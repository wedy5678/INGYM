package ecpay.payment.integration.domain;

/**
 * ?��詢信?��?��?��筆�?�細記�?�物�?
 * @author mark.chiu
 *
 */
public class QueryTradeObj {
	
	/**
	 * MerchantID
	 * ??��?�特店編???(?��ecPay??��??)
	 */
	private String MerchantID = "";
	
	/**
	 * CreditRefundId
	 * 信用?��??��?�單???
	 */
	private String CreditRefundId = "";
	
	/**
	 * CreditAmount
	 * ??��??
	 */
	private String CreditAmount = "";
	
	/**
	 * CreditCheckCode
	 * ??�家檢查�?
	 */
	private String CreditCheckCode = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??��?�MerchantID ??��?�特店編???(?��ecPay??��??)
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * 設�?�MerchantID ??��?�特店編???(?��ecPay??��??)
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * ??��?�CreditRefundId 信用?��??��?�單???
	 * @return CreditRefundId
	 */
	public String getCreditRefundId() {
		return CreditRefundId;
	}
	/**
	 * 設�?�CreditRefundId 信用?��??��?�單???
	 * @param creditRefundId
	 */
	public void setCreditRefundId(String creditRefundId) {
		CreditRefundId = creditRefundId;
	}
	/**
	 * ??��?�CreditAmount ??��??
	 * @return CreditAmount
	 */
	public String getCreditAmount() {
		return CreditAmount;
	}
	/**
	 * 設�?�CreditAmount ??��??
	 * @param creditAmount
	 */
	public void setCreditAmount(String creditAmount) {
		CreditAmount = creditAmount;
	}
	/**
	 * ??��?�CreditCheckCode ??�家檢查�?
	 * @return CreditCheckCode
	 */
	public String getCreditCheckCode() {
		return CreditCheckCode;
	}
	/**
	 * 設�?�CreditCheckCode ??�家檢查�?
	 * @param creditCheckCode
	 */
	public void setCreditCheckCode(String creditCheckCode) {
		CreditCheckCode = creditCheckCode;
	}
	@Override
	public String toString() {
		return "QureyTradeObj [MerchantID=" + MerchantID + ", CreditRefundId=" + CreditRefundId + ", CreditAmount="
				+ CreditAmount + ", CreditCheckCode=" + CreditCheckCode + "]";
	}
}
