package Constants;

public class Constant {

	public String loginvalidationquery = "SELECT m.Password FROM memberlogin m WHERE m.UserId = :userid";
	public String fetchingfailedmemberdata = "FROM FailedMemberData";
	public String fetchingmemberdata = "FROM MemberData";
	
}