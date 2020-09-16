package groupWebsocketTool;

import java.io.IOException;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.model.GroupService;
import com.group.model.GroupVO;
import com.grouplist.model.GroupListService;
import com.grouplist.model.GroupListVO;

public class GroupStatusCheck extends HttpServlet {
	MembersCenter membersCenter = null;
	String groupmessage;
	Timer timer;
	Date today;

	public void destroy() {
		System.out.println("timer destroy!!");
		timer.cancel();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	public void init() {
		timer = new Timer();
		System.out.println("task in!!");

		TimerTask taskTest = new TimerTask() {
			public void run() {
				List<GroupVO> newlist = new GroupService().getAll();
				List<GroupVO> finallist = new GroupService().getAll_G2();
				List<GroupVO> newList = new ArrayList<GroupVO>();// �L�o���ʶ}�l�ɶ�
				List<GroupVO> finalList = new ArrayList<GroupVO>();// �L�o���ʵ����ɶ�
				GroupVO group = null;
				today = new Date();

				newList = newlist.stream().filter(p -> p.getGro_start().getTime() <= today.getTime())
						.collect(Collectors.toList());

				System.out.println("�ܧ󬡰�'�i�椤' = " + newList.size());

				if (finallist.size() != 0) {
					finalList = finallist.stream().filter(e -> e.getGro_end().getTime() <= today.getTime())
							.collect(Collectors.toList());
				}

				membersCenter = new MembersCenter();
//				System.out.println(membersCenter.getSessonKey()); //���ը��osessionKey
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (finalList.size() != 0) {
					for (int i = 0; i < finalList.size(); i++) {
						group = new GroupService().getOneGroup(finalList.get(i).getGro_no());
						group.setGro_status("G3");
						groupmessage = "�A��" + group.getGro_name() + ",���ά��ʵ����o!";

						new GroupService().updateGroup(group.getGro_no(), group.getMem_id(), group.getType_no(),
								group.getGro_name(), group.getGro_intro(), group.getGro_start(), group.getGro_end(),
								group.getGro_addr(), group.getGro_min(), group.getGro_max(), group.getGro_pic(),
								group.getGro_status(), group.getGro_lat(), group.getGro_lng());

						List<GroupListVO> groupMember = new GroupListService().findGroupMember(group.getGro_no());//��έ�

						for (int j = 0; j < groupMember.size(); j++) {
							if (membersCenter.getSessonKey() != null) {
								String message = "{\"type\":\"finalStatus\",\"sender\":\""+group.getGro_no()+"\",\"receiver\":\""
										+ groupMember.get(j).getMem_id() + "\",\"message\":\"" + groupmessage + "\"}";
								System.out.println("send onmessage to Center = " + groupMember.get(j).getMem_id());
								membersCenter.onMessage(membersCenter.getSessionsMap().get(membersCenter.getSessonKey()), message);
							}
						}
					}
				}

				for (int i = 0; i < newList.size(); i++) { // �N�ɶ��쪺���Χ��ܪ��A(�P�_�H�� , >�̤֤H�Ƴ]��G2�i�椤 , <�̤֤H�Ƴ]��G1�������U�[
					group = new GroupService().getOneGroup(newList.get(i).getGro_no());
					if (new GroupListService().findGroupMember(newList.get(i).getGro_no()).size() >= group
							.getGro_min()) {
						group.setGro_status("G2");
						groupmessage = group.getGro_name() + ",�H�ƨ������\�}���o!";

					} else {
						group.setGro_status("G1");
						groupmessage = group.getGro_name() + ",�H�Ƥ����}�Υ���...";

					}
					new GroupService().updateGroup(group.getGro_no(), group.getMem_id(), group.getType_no(),
							group.getGro_name(), group.getGro_intro(), group.getGro_start(), group.getGro_end(),
							group.getGro_addr(), group.getGro_min(), group.getGro_max(), group.getGro_pic(),
							group.getGro_status(), group.getGro_lat(), group.getGro_lng());

					List<GroupListVO> groupMember = new GroupListService().findGroupMember(group.getGro_no());

					for (int j = 0; j < groupMember.size(); j++) {
						if (newList.size() != 0 && membersCenter.getSessonKey() != null) {
							String message = "{\"type\":\"statusCheck\",\"sender\":\""+group.getGro_no()+"\",\"receiver\":\""
									+ groupMember.get(j).getMem_id() + "\",\"message\":\"" + groupmessage + "\"}";
							System.out.println("send onmessage to Center = " + groupMember.get(j).getMem_id());
							membersCenter.onMessage(membersCenter.getSessionsMap().get(membersCenter.getSessonKey()),
									message);
						}
					}
				}
			}
		};
		timer.schedule(taskTest, 0, 30 * 1000);
	}
}
