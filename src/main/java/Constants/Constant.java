package Constants;

public class Constant {

	public static final String loginvalidationquery = "SELECT m.Password FROM memberlogin m WHERE m.UserId = :userid";
	public static final String fetchingfailedmemberdata = "FROM FailedMemberData";
	public static final String fetchingmemberdata = "FROM MemberData";
	public static final String calculatingpremiummaster = "FROM MemberData WHERE Process_Date >= :currentdate AND Process_Date < :nextdate AND Policy_Status = 'I'";
	public static final String fetchingageweightage = "SELECT a.Weightage FROM agereferencetable a WHERE a.Age = :agelimit";
	public static final String fetchinggenderweightage = "SELECT g.Weightage FROM genderreferencetable g WHERE g.Gender = :gender";
	public static final String fetchinghazardousweightage = "SELECT o.Weightage FROM otherfactorsreferencetable o WHERE o.Other_Factors = 'Hazardous Job'";
	public static final String fetchingheartdiseaseweightage = "SELECT o.Weightage FROM otherfactorsreferencetable o WHERE o.Other_Factors = 'Other existing Decease'";
	public static final String fetchingstudentweightage = "SELECT o.Weightage FROM otherfactorsreferencetable o WHERE o.Other_Factors = 'student'";
	public static final String fetchingdrinkingweightage = "SELECT o.Weightage FROM otherfactorsreferencetable o WHERE o.Other_Factors = 'Smoking_Drinking'";
	public static final String fetchingaviationweightage = "SELECT o.Weightage FROM otherfactorsreferencetable o WHERE o.Other_Factors = 'Outdoor'";
	public static final String fetchingcoverageweightage = "SELECT c.Weightage FROM coverageamountreferencetable c WHERE c.Coverage_Amount = :coveragelimit";
	public static final String fetchingpremiumweightage = "SELECT p.Premium FROM premiumreferencetable p WHERE p.Weightage = :weightagelimit";
	public static final String fetchingpremiummaster = "FROM PremiumMaster";
	public static final String fetchingpremiumdata = "FROM PremiumProcess";
	public static final String fetchingpremiumdetails = "FROM PremiumRejectData";
}