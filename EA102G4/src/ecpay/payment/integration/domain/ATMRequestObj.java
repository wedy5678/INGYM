package ecpay.payment.integration.domain;

/**
 * ?��ChoosePayment?��ATM??��?��?��?��?��?��?�知?���?
 * @author mark.chiu
 *
 */
public class ATMRequestObj {
	
	/**
	 * MerchantID
	 * ??��?�特店編???
	 */
	private String MerchantID;
	
	/**
	 * MerchantTradeNo
	 * ??��?�特店交??�編???
	 */
	private String MerchantTradeNo;
	
	/**
	 * StoreID
	 * ??��?�特店�?��?�代碼�?��?��?��?��?�特店填?��店家�?碼使?��
	 */
	private String StoreID;
	
	/**
	 * RtnCode
	 * 交�?��????
	 */
	private String RtnCode;
	
	/**
	 * RtnMsg
	 * 交�?��?�息
	 */
	private String RtnMsg;
	
	/**
	 * TradeNo
	 * 綠�?��?�交??�編???
	 */
	private String TradeNo;
	
	/**
	 * TradeAmt
	 * 交�?��?��??
	 */
	private String TradeAmt;

	/**
	 * PaymentType
	 * ??��?�特店選??��?��?�款?���?
	 */
	private String PaymentType;
	
	/**
	 * PaymentTypeChargeFee
	 * ?�路�? 
	 */
	private String PaymentTypeChargeFee;
	
	/**
	 * TradeDate
	 * 訂單??��?��?��??
	 */
	private String TradeDate;
	
	/**
	 * SimulatePaid
	 * ?��?��?��模擬付款 
	 */
	private String SimulatePaid;
	
	/**
	 * CustomField1
	 * ?��訂�?�稱欄�??1，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField1;
	
	/**
	 * CustomField2
	 * ?��訂�?�稱欄�??2，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField2;
	
	/**
	 * CustomField3
	 * ?��訂�?�稱欄�??3，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField3;
	
	/**
	 * CustomField4
	 * ?��訂�?�稱欄�??4，�?��?��?��?��?��?�使?��記�?�用客製??�使?��欄�??
	 */
	private String CustomField4;
	
	/**
	 * CheckMacValue
	 * 檢查�?
	 */
	private String CheckMacValue;
	
	/**
	 * BankCode
	 * 繳費??行代�?
	 */
	private String BankCode;
	
	/**
	 * vAccount
	 * 繳費??�擬帳�??
	 */
	private String vAccount;
	
	/**
	 * ExpireDate
	 * 繳費??��??
	 */
	private String ExpireDate;
	
	/********************* getters and setters *********************/
	
	/**
	 * ??��?�MerchantID ??��?�特店編???
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * 設�?�MerchantID ??��?�特店編???
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
	 * @param MerchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * ??��?�RtnCode 交�?��????
	 * @return RtnCode
	 */
	public String getRtnCode() {
		return RtnCode;
	}
	/**
	 * 設�?�RtnCode 交�?��????
	 * @param RtnCode
	 */
	public void setRtnCode(String rtnCode) {
		RtnCode = rtnCode;
	}
	/**
	 * ??��?�RtnMsg 交�?��?�息
	 * @return RtnMsg
	 */
	public String getRtnMsg() {
		return RtnMsg;
	}
	/**
	 * 設�?�RtnMsg 交�?��?�息
	 * @param RtnMsg
	 */
	public void setRtnMsg(String rtnMsg) {
		RtnMsg = rtnMsg;
	}
	/**
	 * ??��?�TradeNo 綠�?��?�交??�編???
	 * @return TradeNo
	 */
	public String getTradeNo() {
		return TradeNo;
	}
	/**
	 * 設�?�TradeNo 綠�?��?�交??�編???
	 * @param TradeNo
	 */
	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}
	/**
	 * ??��?�TradeAmt 交�?��?��??
	 * @return TradeAmt
	 */
	public String getTradeAmt() {
		return TradeAmt;
	}
	/**
	 * 設�?�TradeAmt 交�?��?��??
	 * @param TradeAmt
	 */
	public void setTradeAmt(String tradeAmt) {
		TradeAmt = tradeAmt;
	}
	/**
	 * ??��?�PaymentType ??��?�特店選??��?��?�款?���?
	 * @return PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	/**
	 * 設�?�PaymentType ??��?�特店選??��?��?�款?���?
	 * @param PaymentType
	 */
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	/**
	 * ??��?�TradeDate 訂單??��?��?��??
	 * @return TradeDate
	 */
	public String getTradeDate() {
		return TradeDate;
	}
	/**
	 * 設�?�TradeDate 訂單??��?��?��??
	 * @param TradeDate
	 */
	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}
	/**
	 * ??��?�CheckMacValue 檢查�?
	 * @return CheckMacValue
	 */
	public String getCheckMacValue() {
		return CheckMacValue;
	}
	/**
	 * 設�?�CheckMacValue 檢查�?
	 * @param CheckMacValue
	 */
	public void setCheckMacValue(String checkMacValue) {
		CheckMacValue = checkMacValue;
	}
	/**
	 * ??��?�BankCode 繳費??行代�?
	 * @return BankCode
	 */
	public String getBankCode() {
		return BankCode;
	}
	/**
	 * 設�?�BankCode 繳費??行代�?
	 * @param BankCode
	 */
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	/**
	 * ??��?�vAccount 繳費??�擬帳�??
	 * @return vAccount
	 */
	public String getvAccount() {
		return vAccount;
	}
	/**
	 * 設�?�vAccount 繳費??�擬帳�??
	 * @param vAccount
	 */
	public void setvAccount(String vAccount) {
		this.vAccount = vAccount;
	}
	/**
	 * ??��?�ExpireDate 繳費??��??
	 * @return ExpireDate
	 */
	public String getExpireDate() {
		return ExpireDate;
	}
	/**
	 * 設�?�ExpireDate 繳費??��??
	 * @param ExpireDate
	 */
	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
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
	/**
	 * ??��?�PaymentTypeChargeFee ?�路�?
	 * @return PaymentTypeChargeFee
	 */
	public String getPaymentTypeChargeFee() {
		return PaymentTypeChargeFee;
	}
	/**
	 * 設�?�PaymentTypeChargeFee ?�路�?
	 * @param paymentTypeChargeFee
	 */
	public void setPaymentTypeChargeFee(String paymentTypeChargeFee) {
		PaymentTypeChargeFee = paymentTypeChargeFee;
	}
	/**
	 * ??��?�SimulatePaid ?��?��?��模擬付款 
	 * @return SimulatePaid
	 */
	public String getSimulatePaid() {
		return SimulatePaid;
	}
	/**
	 * 設�?�SimulatePaid ?��?��?��模擬付款 
	 * @param simulatePaid
	 */
	public void setSimulatePaid(String simulatePaid) {
		SimulatePaid = simulatePaid;
	}
	@Override
	public String toString() {
		return "ATMRequestObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", StoreID="
				+ StoreID + ", RtnCode=" + RtnCode + ", RtnMsg=" + RtnMsg + ", TradeNo=" + TradeNo + ", TradeAmt="
				+ TradeAmt + ", PaymentType=" + PaymentType + ", PaymentTypeChargeFee=" + PaymentTypeChargeFee
				+ ", TradeDate=" + TradeDate + ", SimulatePaid=" + SimulatePaid + ", CustomField1=" + CustomField1
				+ ", CustomField2=" + CustomField2 + ", CustomField3=" + CustomField3 + ", CustomField4=" + CustomField4
				+ ", CheckMacValue=" + CheckMacValue + ", BankCode=" + BankCode + ", vAccount=" + vAccount
				+ ", ExpireDate=" + ExpireDate + "]";
	}
}
