package constants;

public class TestData {
	public static String loginFail = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><soap:Fault><faultcode>soap:Server</faultcode><faultstring>org.springframework.security.authentication.BadCredentialsException: Bad credentials</faultstring></soap:Fault></soap:Body></soap:Envelope>";
	public static String loginSucceed = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns1:logInResponse xmlns:ns1=\"http://webservices.voiceobjects.com\"><out><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><creationDetails><user>SSA_Heartland_DVARU</user><role>siteAdministrator</role><counter>83066</counter><timestamp>2018-02-06T22:27:17+0000</timestamp><serverVersion>10.0.0.1947</serverVersion><serverPatchVersion>GA</serverPatchVersion><encoding>UTF-8</encoding><metadataVersion>9.4</metadataVersion><repositoryName>VoiceObjects</repositoryName><repositoryID>Default Metadata Repository (20111014175735)</repositoryID><siteID>46759</siteID></creationDetails><commandDetails><command>authenticateUser</command><executionResult>0</executionResult><message>SUCCESS</message></commandDetails><commandResult>OVAP00006a2afc88406d47b13844000001616d2a3c94</commandResult></root>]]></out></ns1:logInResponse></soap:Body></soap:Envelope>";

}
