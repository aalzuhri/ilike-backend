package aaz.ilike.api.common;

public interface IlikeConstants {

	public static final String PublicServletPath = "/aaz/ilike/api/public";
	public static final String PrivateServletPath = "/aaz/ilike/api/private";
	
	//categories controller
	public static final String CategoryServletPath = PublicServletPath + "/categories";
	public static final String CreateCategoryServletPath = PrivateServletPath + "/categories";
	public static final String AllCategoriesServletPath = CategoryServletPath + "/all";
	public static final String DeleteCategoryServletPath = CreateCategoryServletPath;
	
	//book controller
	public static final String BookServletPath = PublicServletPath + "/book";
	public static final String CreateBookServletPath = PrivateServletPath + "/book";
	public static final String AllBooksServletPath = BookServletPath + "/all";
	public static final String DeleteBookServletPath = PrivateServletPath + "/book";
	public static final String BookByIdServletPath = BookServletPath;
	
	//review controller
	public static final String ReviewServletPath = PrivateServletPath + "/review";
	public static final String GetAllReviewsServletPath = PublicServletPath + "/review/all";
	
	//order controller
	public static final String MakeOrderServletPath = PrivateServletPath + "/order";
	
}
