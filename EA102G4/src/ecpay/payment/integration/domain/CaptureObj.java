package ecpay.payment.integration.domain;

/**
 * ??��?�特店申請撥款物�?
 * @author mark.chiu
 *
 */
public class CaptureObj {
	
	/**
	 * MerchantID
	 * ??��?�特店編???(?��綠�?��?��??)
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * ??��?�特店交??�編???
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * CaptureAMT
	 * ??��?�特店申請撥款�?��??
	 */
	private String CaptureAMT = "";
	
	/**
	 * UserRefundAMT
	 * 要�??款給買方??��?��??
	 */
	private String UserRefundAMT = "0";
	
	/**
	 * PlatformID
	 * ?��約�?��?�平?��??�代???(?��綠�?��?��??)
	 */
	private String PlatformID = "";
	
	/**
	 * Remark
	 * ??�註
	 */
	private String Remark = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??��?�MerchantID ??��?�特店編???(?��綠�?��?��??)
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * 設�?�MerchantID ??��?�特店編???(?��綠�?��?��??)
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * ??��?�MerchantTradeNo ??��?�特店交??�編???
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	/**
	 * 設�?�MerchantTradeNo ??��?�特店交??�編???
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * ??��?�CaptureAMT ??��?�特店申請撥款�?��??
	 * @return CaptureAMT
	 */
	public String getCaptureAMT() {
		return CaptureAMT;
	}
	/**
	 * 設�?�CaptureAMT ??��?�特店申請撥款�?��??
	 * @param captureAMT
	 */
	public void setCaptureAMT(String captureAMT) {
		CaptureAMT = captureAMT;
	}
	/**
	 * ??��?�UserRefundAMT 要�??款給買方??��?��??
	 * @return UserRefundAMT
	 */
	public String getUserRefundAMT() {
		return UserRefundAMT;
	}
	/**
	 * 設�?�UserRefundAMT 要�??款給買方??��?��??
	 * @param userRefundAMT
	 */
//	public void setUserRefundAMT(String userRefundAMT) {
//		UserRefundAMT = userRefundAMT;
//	}
	/**
	 * ??��?�PlatformID ?��約�?��?�平?��??�代???(?��綠�?��?��??)
	 * @return PlatformID
	 */
	public String getPlatformID() {
		return PlatformID;
	}
	/**
	 * 設�?�PlatformID ?��約�?��?�平?��??�代???(?��綠�?��?��??)
	 * @param platformID
	 */
	public void setPlatformID(String platformID) {
		PlatformID = platformID;
	}
	/**
	 * ??��?�Remark ??�註
	 * @return Remark
	 */
	public String getRemark() {
		return Remark;
	}
	/**
	 * 設�?�Remark ??�註
	 * @param remark
	 */
	public void setRemark(String remark) {
		Remark = remark;
	}
	@Override
	public String toString() {
		return "CaptureObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", CaptureAMT="
				+ CaptureAMT + ", UserRefundAMT=" + UserRefundAMT + ", PlatformID=" + PlatformID + ", Remark=" + Remark
				+ "]";
	}
}
