package com.swaggy7.licenseweb.utils;

public enum OpeEnum {
	/**
	 * ok
	 */

	//AmController
	Am_getAllAmInfo("/am/getAllAmInfo", "license-查询全部申请信息"),
	Am_insertAmInfo("/am/insertAmInfo", "license-添加申请信息"),
	Am_deleteAmInfo("/am/deleteAmInfo", "license-删除申请信息"),

	//EpController
	Ep_getAllEpInfo("/ep/getAllEpInfo", "license-查询全部回收信息"),
	Ep_insertEpInfo("/ep/insertEpInfo", "license-添加回收信息"),
	Ep_deleteEpInfo("/ep/deleteEpInfo", "license-删除回收信息"),

	//LicenseRegistController
	LicenseRegist_getLicenseInfo("/licenseInfo/getLicenseInfo", "license-通过Lincese名称查询License信息"),
//	LicenseRegist_getAllLicenseInfo("/licenseInfo/getAllLicenseInfo", "license-查询所有License"),
	LicenseRegist_getLicenseName("/licenseInfo/getLicenseName", "license-查询所有License名称"),
//	LicenseRegist_deleteLicense("/licenseInfo/deleteLicense", "license-删除License信息"),
//	LicenseRegist_getDelSocketInfo("/licenseInfo/getDelSocketInfo", "license-"),
	LicenseRegist_insertLicenseInfo("/licenseInfo/insertLicenseInfo", "license-申请License"),
	LicenseRegist_modifyLicenseInfo("/licenseInfo/modifyLicenseInfo", "license-修改License"),

	//RbInfoController
	RbInfo_getRbInfo("/rbInfo/getRbInfo", "license-通过License名称查询节点信息"),
	RbInfo_deleteRbInfo("/rbInfo/deleteLicense", "license-删除节点信息"),

	//SoftController
	Soft_uploadSoft("/soft/uploadSoft", "license-上传软件"),
	Soft_getAllSoftInfo("/soft/getAllSoftInfo", "license-查询所有软件信息"),
	Soft_downloadSoft("/soft/downloadSoft", "license-下载软件"),
	Soft_deleteSoft("/soft/deleteSoft", "license-删除软件"),

	//UsbController
//	Usb_getUsbName("/usb/getUsbName", "license-查询所有Usb名称"),
	Usb_getAllUsbKeys("/usb/getAllUsbKeys", "license-查询所有Usb名称"),
	Usb_uploadUsbKey("/usb/uploadUsbKey", "license-上传UsbKey"),
	Usb_deleteUsbKey("/usb/deleteUsbKey", "license-删除UsbKey"),
	Usb_getUsbName("/usb/getUsbName", "license-查询所有Usb名称"),

	//UserUsbController
	UserUsb_getApplyMonitorInfo("/userUsb/getApplyMonitorInfo", "license-通过Usb名称查询所有UsbKey的申请信息"),
	UserUsb_getRecycleMonitorInfo("/userUsb/getRecycleMonitorInfo", "license-通过Usb名称查询所有UsbKey的回收信息"),
	UserUsb_getUsbInfo("/userUsb/getUsbInfo", "license-获取当前用户的Usb信息"),
	UserUsb_applyUsbKey("/userUsb/applyUsbKey", "license-申请UsbKey"),
	UserUsb_agreeApply("/userUsb/agreeApply", "license-同意用户的UsbKey申请"),
	UserUsb_refuseApply("/userUsb/refuseApply", "license-拒绝用户的UsbKey申请"),
	UserUsb_releaseRecycle("/userUsb/releaseRecycle", "license-回收正在使用的UsbKey"),

	//UserWebController
	UserWeb_applyWeb("/userWeb/applyUserWeb", "license-为当前用户申请网页资源"),
	UserWeb_releaseUserWeb("/userWeb/releaseUserWeb", "license-为当前用户删除网页资源"),
	UserWeb_getUserWebMonitorInfo("/userWeb/getUserWebMonitorInfo", "license-通过Web名称查询所有使用信息"),
	UserWeb_getApplyMonitorInfo("/userWeb/getApplyMonitorInfo", "license-通过Web名称查询所有申请信息"),
	UserWeb_agreeApply("/userWeb/agreeApply", "license-同意用户的Web申请"),
	UserWeb_releaseUse("/userWeb/releaseUse", "license-释放用户的Web资源"),
	UserWeb_refuseApply("/userWeb/refuseApply", "license-拒绝用户的Web申请"),
	UserWeb_agreeAllApply("/userWeb/agreeAllApply", "license-同意用户的所有Web申请"),
	UserWeb_releaseAllUse("/userWeb/releaseAllUse", "license-释放用户的所有Web资源"),

	//WebController
	Web_addWebResource("/web/addWebResource", "license-添加网页资源"),
	Web_getAllWebInfo("/web/getAllWebInfo", "license-查询当前用户的所有网页资源"),
	Web_getWebName("/web/getWebName", "license-查询所有网页资源名称"),
	Web_deleteWeb("/web/deleteWeb", "license-删除网页资源");

	private final String code;

	private final String msg;

	public String value() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	OpeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "OpeEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + "} " + super.toString();
	}

	public static String stringOf(String value) {
		for (OpeEnum oneEnum : OpeEnum.values()) {
			if (oneEnum.code.equals(value)) {
				return oneEnum.getMsg();
			}
		}
		return null;
	}
}
