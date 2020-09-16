package ecpay.payment.integration.domain;

/**
 * ApplePay 信用?��??��?��?�業?���?
 * @author mark.chiu
 *
 */
public class CreateServerOrderObj {
	
	/**
	 * MerchantID
	 * ??��?��?��?�編??��?�由綠�?��?��??
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * ??��?��?��?�交??��?��??
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * MerchantTradeDate
	 * ??��?��?��?�交??��?��??( yyyy/MM/dd HH:mm:ss )
	 */
	private String MerchantTradeDate = "";
	
	/**
	 * TotalAmount
	 * 交�?��?��?��?��?�帶?��?��，�?�可??��?�數點�?��?��?�新?���?，�?��?��?�可?��0???
	 */
	private String TotalAmount = "";
	
	/**
	 * CurrencyCode
	 * �??��，Apple Server??��?��?��?��?��??��??�CurrencyCode
	 */
	private String CurrencyCode = "";
	
	/**
	 * ItemName
	 * ??��?��?�稱，�?��?��?��?��?�稱??��?��?��?��?��?�以井�?��?��??(#)
	 */
	private String ItemName = "";
	
	/**
	 * PlatformID
	 * ?��約�?��?�平?��??�代???
	 */
	private String PlatformID = "";
	
	/**
	 * TradeDesc
	 * 交�?��?�述
	 */
	private String TradeDesc = "";
	
	/**
	 * TradeType
	 * 付款?���?  1. In App   2. On the Web
	 */
	private String TradeType = "";
	
	/**
	 * PaymentToken
	 * 付款資�?�物件�?�Apple Server??��?��?��?��?��?��?�傳??�Merchant Session?��件中payment??�JSON字串
	 */
	private String PaymentToken = "";
	
	/********************* getters and setters *********************/

	/**
	 * ??��?�MerchantID ??��?��?��?�編??��?�由綠�?��?��??
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}

	/**
	 * 設�?�MerchantID ??��?��?��?�編??��?�由綠�?��?��??
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}

	/**
	 * ??��?�MerchantTradeNo ??��?��?��?�交??��?��??
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}

	/**
	 * 設�?�MerchantTradeNo ??��?��?��?�交??��?��??
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}

	/**
	 * ??��?�MerchantTradeDate ??��?��?��?�交??��?��??( yyyy/MM/dd HH:mm:ss )
	 * @return MerchantTradeDate
	 */
	public String getMerchantTradeDate() {
		return MerchantTradeDate;
	}

	/**
	 * 設�?�MerchantTradeDate ??��?��?��?�交??��?��??( yyyy/MM/dd HH:mm:ss )
	 * @param merchantTradeDate
	 */
	public void setMerchantTradeDate(String merchantTradeDate) {
		MerchantTradeDate = merchantTradeDate;
	}

	/**
	 * ??��?�TotalAmount 交�?��?��?��?��?�帶?��?��，�?�可??��?�數點�?��?��?�新?���?，�?��?��?�可?��0???
	 * @return TotalAmount
	 */
	public String getTotalAmount() {
		return TotalAmount;
	}

	/**
	 * 設�?�TotalAmount 交�?��?��?��?��?�帶?��?��，�?�可??��?�數點�?��?��?�新?���?，�?��?��?�可?��0???
	 * @param totalAmount
	 */
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}

	/**
	 * ??��?�CurrencyCode �??��，Apple Server??��?��?��?��?��??��??�CurrencyCode
	 * @return CurrencyCode
	 */
	public String getCurrencyCode() {
		return CurrencyCode;
	}

	/**
	 * 設�?�CurrencyCode �??��，Apple Server??��?��?��?��?��??��??�CurrencyCode
	 * @param currencyCode
	 */
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

	/**
	 * ??��?�ItemName ??��?��?�稱，�?��?��?��?��?�稱??��?��?��?��?��?�以井�?��?��??(#)
	 * @return ItemName
	 */
	public String getItemName() {
		return ItemName;
	}

	/**
	 * 設�?�ItemName ??��?��?�稱，�?��?��?��?��?�稱??��?��?��?��?��?�以井�?��?��??(#)
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	/**
	 * ??��?�PlatformID ?��約�?��?�平?��??�代???
	 * @return PlatformID
	 */
	public String getPlatformID() {
		return PlatformID;
	}

	/**
	 * 設�?�PlatformID ?��約�?��?�平?��??�代???
	 * @param platformID
	 */
	public void setPlatformID(String platformID) {
		PlatformID = platformID;
	}

	/**
	 * ??��?�TradeDesc 交�?��?�述
	 * @return TradeDesc
	 */
	public String getTradeDesc() {
		return TradeDesc;
	}

	/**
	 * 設�?�TradeDesc 交�?��?�述
	 * @param tradeDesc
	 */
	public void setTradeDesc(String tradeDesc) {
		TradeDesc = tradeDesc;
	}

	/**
	 * ??��?�TradeType 付款?���?  1. In App   2. On the Web
	 * @return TradeType
	 */
	public String getTradeType() {
		return TradeType;
	}

	/**
	 * 設�?�TradeType 付款?���?  1. In App   2. On the Web
	 * @param tradeType
	 */
	public void setTradeType(String tradeType) {
		TradeType = tradeType;
	}

	/**
	 * ??��?�PaymentToken 付款資�?�物件�?�Apple Server??��?��?��?��?��?��?�傳??�Merchant Session?��件中payment??�JSON字串
	 * @return PaymentToken
	 */
	public String getPaymentToken() {
		return PaymentToken;
	}

	/**
	 * 設�?�PaymentToken 付款資�?�物件�?�Apple Server??��?��?��?��?��?��?�傳??�Merchant Session?��件中payment??�JSON字串
	 * @param paymentToken
	 */
	public void setPaymentToken(String paymentToken) {
		PaymentToken = paymentToken;
	}

	@Override
	public String toString() {
		return "CreateServerOrderObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo
				+ ", MerchantTradeDate=" + MerchantTradeDate + ", TotalAmount=" + TotalAmount + ", CurrencyCode="
				+ CurrencyCode + ", ItemName=" + ItemName + ", PlatformID=" + PlatformID + ", TradeDesc=" + TradeDesc
				+ ", TradeType=" + TradeType + ", PaymentToken=" + PaymentToken + "]";
	}
}
