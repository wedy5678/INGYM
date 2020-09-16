package ecpay.payment.integration.domain;

/**
 * ?��詢�?�單?���?
 * @author mark.chiu
 *
 */
public class QueryTradeInfoObj {
	
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
	 * TimeStamp
	 * 驗�?��?��??
	 */
	private String TimeStamp = "";
	
	/**
	 * PlatformID
	 * ?��約�?��?�平?��??�代???(?��綠�?��?��??)
	 */
	private String PlatformID = "";
	
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
	 * ??��?�TimeStamp 驗�?��?��??
	 * @return TimeStamp
	 */
	public String getTimeStamp() {
		return TimeStamp;
	}
	/**
	 * 設�?�TimeStamp 驗�?��?��??
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
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
	@Override
	public String toString() {
		return "QueryTradeInfoObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", TimeStamp="
				+ TimeStamp + ", PlatformID=" + PlatformID + "]";
	}
}
