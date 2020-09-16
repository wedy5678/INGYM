package ecpay.payment.integration.domain;

/**
 * 下�?��?��?�特店�?�帳媒�?��?�物�?
 * @author mark.chiu
 *
 */
public class TradeNoAioObj {
	
	/**
	 * MerchantID
	 * ??��?�特店編???(?��綠�?��?��??)
	 */
	private String MerchantID = "";
	
	/**
	 * DateType
	 * ?��詢日??��?�別
	 */
	private String DateType = "";
	
	/**
	 * BeginDate
	 * ?��詢�?��?�日???
	 */
	private String BeginDate = "";
	
	/**
	 * EndDate
	 * ?��詢�?��?�日???
	 */
	private String EndDate = "";
	
	/**
	 * PaymentType
	 * 付款?���?
	 */
	private String PaymentType = "";
	
	/**
	 * PlatformStatus
	 * 訂單類�??
	 */
	private String PlatformStatus = "";
	
	/**
	 * PaymentStatus
	 * 付款?????
	 */
	private String PaymentStatus = "";
	
	/**
	 * AllocateStatus
	 * ?��款�????
	 */
	private String AllocateStatus = "";
	
	/**
	 * MediaFormatted
	 * CSV?���?
	 */
	private String MediaFormated = "";
	
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
	 * ??��?�DateType ?��詢日??��?�別
	 * @return DateType
	 */
	public String getDateType() {
		return DateType;
	}
	/**
	 * 設�?�DateType ?��詢日??��?�別
	 * @param dateType
	 */
	public void setDateType(String dateType) {
		DateType = dateType;
	}
	/**
	 * ??��?�BeginDate ?��詢�?��?�日???
	 * @return BeginDate
	 */
	public String getBeginDate() {
		return BeginDate;
	}
	/**
	 * 設�?�BeginDate ?��詢�?��?�日???
	 * @param beginDate
	 */
	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
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
	/**
	 * ??��?�PaymentType 付款?���?
	 * @return PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	/**
	 * 設�?�PaymentType 付款?���?
	 * @param paymentType
	 */
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	/**
	 * ??��?�PlatformStatus 訂單類�??
	 * @return PlatformStatus
	 */
	public String getPlatformStatus() {
		return PlatformStatus;
	}
	/**
	 * 設�?�PlatformStatus 訂單類�??
	 * @param platformStatus
	 */
	public void setPlatformStatus(String platformStatus) {
		PlatformStatus = platformStatus;
	}
	/**
	 * ??��?�PaymentStatus 付款?????
	 * @return PaymentStatus
	 */
	public String getPaymentStatus() {
		return PaymentStatus;
	}
	/**
	 * 設�?�PaymentStatus 付款?????
	 * @param paymentStatus
	 */
	public void setPaymentStatus(String paymentStatus) {
		PaymentStatus = paymentStatus;
	}
	/**
	 * ??��?�AllocateStatus ?��款�????
	 * @return AllocateStatus
	 */
	public String getAllocateStatus() {
		return AllocateStatus;
	}
	/**
	 * 設�?�AllocateStatus ?��款�????
	 * @param allocateStatus
	 */
	public void setAllocateStatus(String allocateStatus) {
		AllocateStatus = allocateStatus;
	}
	/**
	 * ??��?�MediaFormatted CSV?���?
	 * @return MediaFormatted
	 */
	public String getMediaFormated() {
		return MediaFormated;
	}
	/**
	 * 設�?�MediaFormatted CSV?���?
	 * @param mediaFormated
	 */
	public void setMediaFormated(String mediaFormated) {
		MediaFormated = mediaFormated;
	}
	@Override
	public String toString() {
		return "TradeNoAioObj [MerchantID=" + MerchantID + ", DateType=" + DateType + ", BeginDate=" + BeginDate
				+ ", EndDate=" + EndDate + ", PaymentType=" + PaymentType + ", PlatformStatus=" + PlatformStatus
				+ ", PaymentStatus=" + PaymentStatus + ", AllocateStatus=" + AllocateStatus + ", MediaFormated="
				+ MediaFormated + "]";
	}
}
