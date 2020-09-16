package ecpay.payment.integration.domain;

/**
 * ??��?�發票物�?
 * @author mark.chiu
 *
 */
public class InvoiceObj {
	
	/**
	 * RelateNumber
	 * ??��?�特店自訂編???
	 */
	private String RelateNumber = "";
	
	/**
	 * CustomerID
	 * 客戶�????
	 */
	private String CustomerID = "";
	
	/**
	 * CustomerIdentifier
	 * 統�?編�??
	 */
	private String CustomerIdentifier = "";
	
	/**
	 * CustomerName
	 * 客戶??�稱
	 */
	private String CustomerName = "";
	
	/**
	 * CustomerAddr
	 * 客戶?��??
	 */
	private String CustomerAddr = "";
	
	/**
	 * CustomerPhone
	 * 客戶??��?��?�碼
	 */
	private String CustomerPhone = "";
	
	/**
	 * CustomerEmail
	 * 客戶?��子信�?
	 */
	private String CustomerEmail = "";
	
	/**
	 * ClearanceMark
	 * ?��?�方�?
	 */
	private String ClearanceMark = "";
	
	/**
	 * TaxType
	 * 課�?��?�別
	 */
	private String TaxType = "";
	
	/**
	 * CarruerType
	 * 載具類別
	 */
	private String CarruerType = "";
	
	/**
	 * CarruerNum
	 * 載具編�??
	 */
	private String CarruerNum = "";
	
	/**
	 * Donation
	 * ??��?�註�?
	 */
	private String Donation = "";
	
	/**
	 * LoveCode
	 * ??��?�碼
	 */
	private String LoveCode = "";
	
	/**
	 * Print
	 * ??�印註�??
	 */
	private String Print = "";
	
	/**
	 * InvoiceItemName
	 * ??��?��?�稱
	 */
	private String InvoiceItemName = "";
	
	/**
	 * InvoiceItemCount
	 * ??��?�數???
	 */
	private String InvoiceItemCount = "";
	
	/**
	 * InvoiceItemWord
	 * ??��?�單�?
	 */
	private String InvoiceItemWord = "";
	
	/**
	 * InvoiceItemPrice
	 * ??��?�價?��
	 */
	private String InvoiceItemPrice = "";
	
	/**
	 * InvoiceItemTaxType
	 * ??��?�課稅別
	 */
	private String InvoiceItemTaxType = "";
	
	/**
	 * InvoiceRemark
	 * ??�註
	 */
	private String InvoiceRemark = "";
	
	/**
	 * DelayDay
	 * 延遲天數
	 */
	private String DelayDay = "";
	
	/**
	 * InvType
	 * 字�?��?�別
	 */
	private String InvType = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * ??��?�RelateNumber ??��?�特店自訂編???
	 * @return RelateNumber
	 */
	public String getRelateNumber() {
		return RelateNumber;
	}
	/**
	 * 設�?�RelateNumber ??��?�特店自訂編???
	 * @param relateNumber
	 */
	public void setRelateNumber(String relateNumber) {
		RelateNumber = relateNumber;
	}
	/**
	 * ??��?�CustomerID 客戶�????
	 * @return CustomerID
	 */
	public String getCustomerID() {
		return CustomerID;
	}
	/**
	 * 設�?�CustomerID 客戶�????
	 * @param customerID
	 */
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	/**
	 * ??��?�CustomerIdentifier 統�?編�??
	 * @return CustomerIdentifier
	 */
	public String getCustomerIdentifier() {
		return CustomerIdentifier;
	}
	/**
	 * 設�?�CustomerIdentifier 統�?編�??
	 * @param customerIdentifier
	 */
	public void setCustomerIdentifier(String customerIdentifier) {
		CustomerIdentifier = customerIdentifier;
	}
	/**
	 * ??��?�CustomerName 客戶??�稱
	 * @return CustomerName
	 */
	public String getCustomerName() {
		return CustomerName;
	}
	/**
	 * 設�?�CustomerName 客戶??�稱
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	/**
	 * ??��?�CustomerAddr 客戶?��??
	 * @return CustomerAddr
	 */
	public String getCustomerAddr() {
		return CustomerAddr;
	}
	/**
	 * 設�?�CustomerAddr 客戶?��??
	 * @param customerAddr
	 */
	public void setCustomerAddr(String customerAddr) {
		CustomerAddr = customerAddr;
	}
	/**
	 * ??��?�CustomerPhone 客戶??��?��?�碼
	 * @return CustomerPhone
	 */
	public String getCustomerPhone() {
		return CustomerPhone;
	}
	/**
	 * 設�?�CustomerPhone 客戶??��?��?�碼
	 * @param customerPhone
	 */
	public void setCustomerPhone(String customerPhone) {
		CustomerPhone = customerPhone;
	}
	/**
	 * ??��?�CustomerEmail 客戶?��子信�?
	 * @return CustomerEmail
	 */
	public String getCustomerEmail() {
		return CustomerEmail;
	}
	/**
	 * 設�?�CustomerEmail 客戶?��子信�?
	 * @param customerEmail
	 */
	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}
	/**
	 * ??��?�ClearanceMark ?��?�方�?
	 * @return ClearanceMark
	 */
	public String getClearanceMark() {
		return ClearanceMark;
	}
	/**
	 * 設�?�ClearanceMark ?��?�方�?
	 * @param clearanceMark
	 */
	public void setClearanceMark(String clearanceMark) {
		ClearanceMark = clearanceMark;
	}
	/**
	 * ??��?�TaxType 課�?��?�別
	 * @return TaxType
	 */
	public String getTaxType() {
		return TaxType;
	}
	/**
	 * 設�?�TaxType 課�?��?�別
	 * @param taxType
	 */
	public void setTaxType(String taxType) {
		TaxType = taxType;
	}
	/**
	 * ??��?�CarruerType 載具類別
	 * @return CarruerType
	 */
	public String getCarruerType() {
		return CarruerType;
	}
	/**
	 * 設�?�CarruerType 載具類別
	 * @param carruerType
	 */
	public void setCarruerType(String carruerType) {
		CarruerType = carruerType;
	}
	/**
	 * ??��?�CarruerNum 載具編�??
	 * @return CarruerNum
	 */
	public String getCarruerNum() {
		return CarruerNum;
	}
	/**
	 * 設�?�CarruerNum 載具編�??
	 * @param carruerNum
	 */
	public void setCarruerNum(String carruerNum) {
		CarruerNum = carruerNum;
	}
	/**
	 * ??��?�Donation ??��?�註�?
	 * @return Donation
	 */
	public String getDonation() {
		return Donation;
	}
	/**
	 * 設�?�Donation ??��?�註�?
	 * @param donation
	 */
	public void setDonation(String donation) {
		Donation = donation;
	}
	/**
	 * ??��?�LoveCode ??��?�碼
	 * @return LoveCode
	 */
	public String getLoveCode() {
		return LoveCode;
	}
	/**
	 * 設�?�LoveCode ??��?�碼
	 * @param loveCode
	 */
	public void setLoveCode(String loveCode) {
		LoveCode = loveCode;
	}
	/**
	 * ??��?�Print ??�印註�??
	 * @return Print
	 */
	public String getPrint() {
		return Print;
	}
	/**
	 * 設�?�Print ??�印註�??
	 * @param print
	 */
	public void setPrint(String print) {
		Print = print;
	}
	/**
	 * ??��?�InvoiceItemName ??��?��?�稱
	 * @return InvoiceItemName
	 */
	public String getInvoiceItemName() {
		return InvoiceItemName;
	}
	/**
	 * 設�?�InvoiceItemName ??��?��?�稱
	 * @param invoiceItemName
	 */
	public void setInvoiceItemName(String invoiceItemName) {
		InvoiceItemName = invoiceItemName;
	}
	/**
	 * ??��?�InvoiceItemCount ??��?�數???
	 * @return InvoiceItemCount
	 */
	public String getInvoiceItemCount() {
		return InvoiceItemCount;
	}
	/**
	 * 設�?�InvoiceItemCount ??��?�數???
	 * @param invoiceItemCount
	 */
	public void setInvoiceItemCount(String invoiceItemCount) {
		InvoiceItemCount = invoiceItemCount;
	}
	/**
	 * ??��?�InvoiceItemWord ??��?�單�?
	 * @return InvoiceItemWord
	 */
	public String getInvoiceItemWord() {
		return InvoiceItemWord;
	}
	/**
	 * 設�?�InvoiceItemWord ??��?�單�?
	 * @param invoiceItemWord
	 */
	public void setInvoiceItemWord(String invoiceItemWord) {
		InvoiceItemWord = invoiceItemWord;
	}
	/**
	 * ??��?�InvoiceItemPrice ??��?�價?��
	 * @return InvoiceItemPrice
	 */
	public String getInvoiceItemPrice() {
		return InvoiceItemPrice;
	}
	/**
	 * 設�?�InvoiceItemPrice ??��?�價?��
	 * @param invoiceItemPrice
	 */
	public void setInvoiceItemPrice(String invoiceItemPrice) {
		InvoiceItemPrice = invoiceItemPrice;
	}
	/**
	 * ??��?�InvoiceItemTaxType ??��?�課稅別
	 * @return InvoiceItemTaxType
	 */
	public String getInvoiceItemTaxType() {
		return InvoiceItemTaxType;
	}
	/**
	 * 設�?�InvoiceItemTaxType ??��?�課稅別
	 * @param invoiceItemTaxType
	 */
	public void setInvoiceItemTaxType(String invoiceItemTaxType) {
		InvoiceItemTaxType = invoiceItemTaxType;
	}
	/**
	 * ??��?�InvoiceRemark ??�註
	 * @return InvoiceRemark
	 */
	public String getInvoiceRemark() {
		return InvoiceRemark;
	}
	/**
	 * 設�?�InvoiceRemark ??�註
	 * @param invoiceRemark
	 */
	public void setInvoiceRemark(String invoiceRemark) {
		InvoiceRemark = invoiceRemark;
	}
	/**
	 * ??��?�DelayDay 延遲天數
	 * @return DelayDay
	 */
	public String getDelayDay() {
		return DelayDay;
	}
	/**
	 * 設�?�DelayDay 延遲天數
	 * @param delayDay
	 */
	public void setDelayDay(String delayDay) {
		DelayDay = delayDay;
	}
	/**
	 * ??��?�InvType 字�?��?�別
	 * @return InvType
	 */
	public String getInvType() {
		return InvType;
	}
	/**
	 * 設�?�InvType 字�?��?�別
	 * @param invType
	 */
	public void setInvType(String invType) {
		InvType = invType;
	}
	@Override
	public String toString() {
		return "InvoiceObj [RelateNumber=" + RelateNumber + ", CustomerID=" + CustomerID + ", CustomerIdentifier="
				+ CustomerIdentifier + ", CustomerName=" + CustomerName + ", CustomerAddr=" + CustomerAddr
				+ ", CustomerPhone=" + CustomerPhone + ", CustomerEmail=" + CustomerEmail + ", ClearanceMark="
				+ ClearanceMark + ", TaxType=" + TaxType + ", CarruerType=" + CarruerType + ", CarruerNum=" + CarruerNum
				+ ", Donation=" + Donation + ", LoveCode=" + LoveCode + ", Print=" + Print + ", InvoiceItemName="
				+ InvoiceItemName + ", InvoiceItemCount=" + InvoiceItemCount + ", InvoiceItemWord=" + InvoiceItemWord
				+ ", InvoiceItemPrice=" + InvoiceItemPrice + ", InvoiceItemTaxType=" + InvoiceItemTaxType
				+ ", InvoiceRemark=" + InvoiceRemark + ", DelayDay=" + DelayDay + ", InvType=" + InvType + "]";
	}
}
