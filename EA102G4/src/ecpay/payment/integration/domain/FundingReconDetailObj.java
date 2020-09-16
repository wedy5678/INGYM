package ecpay.payment.integration.domain;

/**
 * 下�?�信?��?��?��款�?�帳資�?��?�物�?
 * @author mark.chiu
 *
 */
public class FundingReconDetailObj {
	
	/**
	 * MerchantID
	 * ??�員編�??(?��allPay??��??)
	 */
	private String MerchantID = "";
	
	/**
	 * PayDateType
	 * ?��詢日??��?�別
	 */
	private String PayDateType = "";
	
	/**
	 * StartDate
	 * ?��詢�?��?�日???
	 */
	private String StartDate = "";
	
	/**
	 * EndDate
	 * ?��詢�?��?�日???
	 */
	private String EndDate = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??��?�MerchantID ??�員編�??(?��allPay??��??)
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * 設�?�MerchantID ??�員編�??(?��allPay??��??)
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * ??��?�PayDateType ?��詢日??��?�別
	 * @return PayDateType
	 */
	public String getPayDateType() {
		return PayDateType;
	}
	/**
	 * 設�?�PayDateType ?��詢日??��?�別
	 * @param payDateType
	 */
	public void setPayDateType(String payDateType) {
		PayDateType = payDateType;
	}
	/**
	 * ??��?�StartDate ?��詢�?��?�日???
	 * @return StartDate
	 */
	public String getStartDate() {
		return StartDate;
	}
	/**
	 * 設�?�StartDate ?��詢�?��?�日???
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	/**
	 * ??��?�EndDate ?��詢�?��?�日???
	 * @return EndDate
	 */
	public String getEndDate() {
		return EndDate;
	}
	/**
	 * 設�?�EndDate ?��詢�?��?�日???
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	@Override
	public String toString() {
		return "FundingReconDetailObj [MerchantID=" + MerchantID + ", PayDateType=" + PayDateType + ", StartDate="
				+ StartDate + ", EndDate=" + EndDate + "]";
	}
}
