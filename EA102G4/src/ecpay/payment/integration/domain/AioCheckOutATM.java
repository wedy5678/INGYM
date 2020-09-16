package ecpay.payment.integration.domain;

/**
 * ?��??�ATM訂單?���?
 * @author mark.chiu
 *
 */
public class AioCheckOutATM {
	
	/**
	 * MerchantID
	 * ??��?�特店編???(?��綠�?��?��??)
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * ??��?�特店交??�編???(?��??��?�特店�?��??)，該交�?�編??��?�可??��??
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * MerchantTradeDate
	 * ??��?�特店交??��?��??
	 */
	private String MerchantTradeDate = "";
	
	/**
	 * PaymentType
	 * 交�?��?��??
	 */
	private String PaymentType = "aio";
	
	/**
	 * TotalAmount
	 * 交�?��?��??
	 */
	private String TotalAmount = "";
	
	/**
	 * TradeDesc
	 * 交�?��?�述
	 */
	private String TradeDesc = "";
	
	/**
	 * ItemName
	 * ??��?��?�稱
	 */
	private String ItemName = "";
	
	/**
	 * ReturnURL
	 * 付款完�?��?�知??�傳網�?
	 */
	private String ReturnURL = "";
	
	/**
	 * ChoosePayment
	 * ?��??��?�設付款?���?
	 */
	private String ChoosePayment = "ATM";
	
	/**
	 * ClientBackURL
	 * Client端�?��?��?��?�特店系統�?��?��?��??�?
	 */
	private String ClientBackURL = "";
	
	/**
	 * ItemURL
	 * ??��?�銷?��網�?
	 */
	private String ItemURL = "";
	
	/**
	 * Remark
	 * ??�註欄�??
	 */
	private String Remark = "";
	
	/**
	 * ChooseSubPayment
	 * ?��??��?�設付款子�?�目
	 */
	private String ChooseSubPayment = "";
	
	/**
	 * OrderResultURL
	 * Client端�?�傳付款結�?�網??
	 */
	private String OrderResultURL = "";
	
	/**
	 * NeedExtraPaidInfo
	 * ?��?��??要�?��?��?��?�款資�??
	 */
	private String NeedExtraPaidInfo = "";
	
	/**
	 * DeviceSource
	 * 裝置來�??
	 */
	private String DeviceSource = "";
	
	/**
	 * IgnorePayment
	 * ?��??��?�款?���?
	 */
	private String IgnorePayment = "";
	
	/**
	 * PlatformID
	 * ?��約�?��?�平?��??�代???(?��綠�?��?��??)
	 */
	private String PlatformID = "";
	
	/**
	 * InvoiceMark
	 * ?��子發票�?��?�註�?
	 */
	private String InvoiceMark = "";
	
	/**
	 * EncryptType
	 * CheckMacValue??��?��?��??
	 */
	private String EncryptType = "1";
	
	/**
	 * ExpireDate
	 * ??�許繳費??��?�天?��
	 */
	private String ExpireDate = "";
	
	/**
	 * PaymentInfoURL
	 * Server端�?�傳付款?��??��?��??
	 */
	private String PaymentInfoURL = "";
	
	/**
	 * ClientRedirectURL
	 * Client端�?�傳付款?��??��?��??
	 */
	private String ClientRedirectURL = "";
	
	/**
	 * StoreID
	 * ??��?�特店�?��?�代碼�?��?��?��?��?�特店填?��店家�?碼使?��
	 */
	private String StoreID = "";
	
	/**
	 * CustomField1
	 * ?��訂�?�稱欄�??1，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField1 = "";
	
	/**
	 * CustomField2
	 * ?��訂�?�稱欄�??2，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField2 = "";
	
	/**
	 * CustomField3
	 * ?��訂�?�稱欄�??3，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField3 = "";
	
	/**
	 * CustomField4
	 * ?��訂�?�稱欄�??4，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField4 = "";
	
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
	 * ??��?�MerchantTradeNo ??��?�特店交??�編???(?��??��?�特店�?��??)，該交�?�編??��?�可??��??
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	/**
	 * 設�?�MerchantTradeNo ??��?�特店交??�編???(?��??��?�特店�?��??)，該交�?�編??��?�可??��??
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * ??��?�MerchantTradeDate ??��?�特店交??��?��??
	 * @return MerchantTradeDate
	 */
	public String getMerchantTradeDate() {
		return MerchantTradeDate;
	}
	/**
	 * 設�?�MerchantTradeDate ??��?�特店交??��?��?��?��?�以 yyyy/MM/dd HH:mm:ss?��式帶?��
	 * @param merchantTradeDate
	 */
	public void setMerchantTradeDate(String merchantTradeDate) {
		MerchantTradeDate = merchantTradeDate;
	}
	/**
	 * ??��?�PaymentType 交�?��?��??
	 * @return PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	/**
	 * 設�?�PaymentType 交�?��?��??
	 * @param paymentType
	 */
//	public void setPaymentType(String paymentType) {
//		PaymentType = paymentType;
//	}
	/**
	 * ??��?�TotalAmount 交�?��?��??
	 * @return TotalAmount
	 */
	public String getTotalAmount() {
		return TotalAmount;
	}
	/**
	 * 設�?�TotalAmount 交�?��?��??
	 * @param totalAmount
	 */
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
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
	 * ??��?�ItemName ??��?��?�稱
	 * @return ItemName
	 */
	public String getItemName() {
		return ItemName;
	}
	/**
	 * 設�?�ItemName ??��?��?�稱
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	/**
	 * ??��?�ReturnURL 付款完�?��?�知??�傳網�?
	 * @return ReturnURL
	 */
	public String getReturnURL() {
		return ReturnURL;
	}
	/**
	 * 設�?�ReturnURL 付款完�?��?�知??�傳網�?
	 * @param returnURL
	 */
	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}
	/**
	 * ??��?�ChoosePayment ?��??��?�設付款?���?
	 * @return ChoosePayment
	 */
	public String getChoosePayment() {
		return ChoosePayment;
	}
	/**
	 * 設�?�ChoosePayment ?��??��?�設付款?���?
	 * @param choosePayment
	 */
//	public void setChoosePayment(String choosePayment) {
//		ChoosePayment = choosePayment;
//	}
	/**
	 * ??��?�ClientBackURL Client端�?��?��?��?�特店系統�?��?��?��??�?
	 * @return ClientBackURL
	 */
	public String getClientBackURL() {
		return ClientBackURL;
	}
	/**
	 * 設�?�ClientBackURL Client端�?��?��?��?�特店系統�?��?��?��??�?
	 * @param clientBackURL
	 */
	public void setClientBackURL(String clientBackURL) {
		ClientBackURL = clientBackURL;
	}
	/**
	 * ??��?�ItemURL ??��?�銷?��網�?
	 * @return ItemURL
	 */
	public String getItemURL() {
		return ItemURL;
	}
	/**
	 * 設�?? ItemURL ??��?�銷?��網�?
	 * @param itemURL
	 */
	public void setItemURL(String itemURL) {
		ItemURL = itemURL;
	}
	/**
	 * ??��?�Remark ??�註欄�??
	 * @return Remark
	 */
	public String getRemark() {
		return Remark;
	}
	/**
	 * 設�?�Remark ??�註欄�??
	 * @param remark
	 */
	public void setRemark(String remark) {
		Remark = remark;
	}
	/**
	 * ??��?�ChooseSubPayment ?��??��?�設付款子�?�目
	 * @return ChooseSubPayment
	 */
	public String getChooseSubPayment() {
		return ChooseSubPayment;
	}
	/**
	 * 設�?�ChooseSubPayment ?��??��?�設付款子�?�目
	 * @param chooseSubPayment
	 */
	public void setChooseSubPayment(String chooseSubPayment) {
		ChooseSubPayment = chooseSubPayment;
	}
	/**
	 * ??��?�OrderResultURL Client端�?�傳付款結�?�網??
	 * @return OrderResultURL
	 */
	public String getOrderResultURL() {
		return OrderResultURL;
	}
	/**
	 * 設�?�OrderResultURL Client端�?�傳付款結�?�網??
	 * @param orderResultURL
	 */
	public void setOrderResultURL(String orderResultURL) {
		OrderResultURL = orderResultURL;
	}
	/**
	 * ??��?�NeedExtraPaidInfo ?��?��??要�?��?��?��?�款資�?? 
	 * @return NeedExtraPaidInfo
	 */
	public String getNeedExtraPaidInfo() {
		return NeedExtraPaidInfo;
	}
	/**
	 * 設�?�NeedExtraPaidInfo ?��?��??要�?��?��?��?�款資�?? 
	 * @param needExtraPaidInfo
	 */
	public void setNeedExtraPaidInfo(String needExtraPaidInfo) {
		NeedExtraPaidInfo = needExtraPaidInfo;
	}
	/**
	 * ??��?�DeviceSource 裝置來�??
	 * @return DeviceSource
	 */
	public String getDeviceSource() {
		return DeviceSource;
	}
	/**
	 * 設�?�DeviceSource 裝置來�??
	 * @param deviceSource
	 */
	public void setDeviceSource(String deviceSource) {
		DeviceSource = deviceSource;
	}
	/**
	 * ??��?�IgnorePayment ?��??��?�款?���?
	 * @return IgnorePayment
	 */
	public String getIgnorePayment() {
		return IgnorePayment;
	}
	/**
	 * 設�?�IgnorePayment ?��??��?�款?���?
	 * @param ignorePayment
	 */
	public void setIgnorePayment(String ignorePayment) {
		IgnorePayment = ignorePayment;
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
	/**
	 * ??��?�InvoiceMark ?��子發票�?��?�註�?
	 * @return InvoiceMark
	 */
	public String getInvoiceMark() {
		return InvoiceMark;
	}
	/**
	 * 設�?�InvoiceMark ?��子發票�?��?�註�?
	 * @param invoiceMark
	 */
	public void setInvoiceMark(String invoiceMark) {
		InvoiceMark = invoiceMark;
	}
	/**
	 * ??��?�EncryptType CheckMacValue??��?��?��??
	 * @return EncryptType
	 */
	public String getEncryptType() {
		return EncryptType;
	}
	/**
	 * 設�?�EncryptType CheckMacValue??��?��?��??
	 * @param encryptType
	 */
//	public void setEncryptType(String encryptType) {
//		EncryptType = encryptType;
//	}
	/**
	 * ??��?�ExpireDate ??�許繳費??��?�天?��
	 * @return ExpireDate
	 */
	public String getExpireDate() {
		return ExpireDate;
	}
	
	/**
	 * 設�?�ExpireDate ??�許繳費??��?�天?��
	 * @param expireDate
	 */
	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
	}
	/**
	 * ??��?�PaymentInfoURL Server端�?�傳付款?��??��?��??
	 * @return PaymentInfoURL
	 */
	public String getPaymentInfoURL() {
		return PaymentInfoURL;
	}
	/**
	 * 設�?�PaymentInfoURL Server端�?�傳付款?��??��?��??
	 * @param paymentInfoURL
	 */
	public void setPaymentInfoURL(String paymentInfoURL) {
		PaymentInfoURL = paymentInfoURL;
	}
	/**
	 * ??��?�ClientRedirectURL Client端�?�傳付款?��??��?��??
	 * @return ClientRedirectURL
	 */
	public String getClientRedirectURL() {
		return ClientRedirectURL;
	}
	/**
	 * 設�?�ClientRedirectURL Client端�?�傳付款?��??��?��??
	 * @param clientRedirectURL
	 */
	public void setClientRedirectURL(String clientRedirectURL) {
		ClientRedirectURL = clientRedirectURL;
	}
	/**
	 * ??��?�StoreID ??��?�特店�?��?�代碼�?��?��?��?��?�特店填?��店家�?碼使?��
	 * @return StoreID
	 */
	public String getStoreID() {
		return StoreID;
	}
	/**
	 * 設�?�StoreID ??��?�特店�?��?�代碼�?��?��?��?��?�特店填?��店家�?碼使?��
	 * @param storeID
	 */
	public void setStoreID(String storeID) {
		StoreID = storeID;
	}
	/**
	 * ??��?�CustomField1 ?��訂�?�稱欄�??1，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @return CustomField1
	 */
	public String getCustomField1() {
		return CustomField1;
	}
	/**
	 * 設�?�CustomField1 ?��訂�?�稱欄�??1，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @param customField1
	 */
	public void setCustomField1(String customField1) {
		CustomField1 = customField1;
	}
	/**
	 * ??��?�CustomField2 ?��訂�?�稱欄�??2，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @return CustomField2
	 */
	public String getCustomField2() {
		return CustomField2;
	}
	/**
	 * 設�?�CustomField2 ?��訂�?�稱欄�??2，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @param customField2
	 */
	public void setCustomField2(String customField2) {
		CustomField2 = customField2;
	}
	/**
	 * ??��?�CustomField3 ?��訂�?�稱欄�??3，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @return CustomField3
	 */
	public String getCustomField3() {
		return CustomField3;
	}
	/**
	 * 設�?�CustomField3 ?��訂�?�稱欄�??3，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @param customField3
	 */
	public void setCustomField3(String customField3) {
		CustomField3 = customField3;
	}
	/**
	 * ??��?�CustomField4 ?��訂�?�稱欄�??4，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @return CustomField4
	 */
	public String getCustomField4() {
		return CustomField4;
	}
	/**
	 * 設�?�CustomField4 ?��訂�?�稱欄�??4，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 * @param customField4
	 */
	public void setCustomField4(String customField4) {
		CustomField4 = customField4;
	}
	@Override
	public String toString() {
		return "AioCheckOutATM [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo
				+ ", MerchantTradeDate=" + MerchantTradeDate + ", PaymentType=" + PaymentType + ", TotalAmount="
				+ TotalAmount + ", TradeDesc=" + TradeDesc + ", ItemName=" + ItemName + ", ReturnURL=" + ReturnURL
				+ ", ChoosePayment=" + ChoosePayment + ", ClientBackURL=" + ClientBackURL + ", ItemURL=" + ItemURL
				+ ", Remark=" + Remark + ", ChooseSubPayment=" + ChooseSubPayment + ", OrderResultURL=" + OrderResultURL
				+ ", NeedExtraPaidInfo=" + NeedExtraPaidInfo + ", DeviceSource=" + DeviceSource + ", IgnorePayment="
				+ IgnorePayment + ", PlatformID=" + PlatformID + ", InvoiceMark=" + InvoiceMark + ", EncryptType=" + EncryptType + ", ExpireDate=" + ExpireDate + ", PaymentInfoURL="
				+ PaymentInfoURL + ", ClientRedirectURL=" + ClientRedirectURL + ", StoreID=" + StoreID
				+ ", CustomField1=" + CustomField1 + ", CustomField2=" + CustomField2 + ", CustomField3=" + CustomField3
				+ ", CustomField4=" + CustomField4 + "]";
	}
}
