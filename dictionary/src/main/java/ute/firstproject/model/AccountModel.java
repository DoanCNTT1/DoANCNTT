package ute.firstproject.model;
import java.sql.Date;

public class AccountModel {
	private String tenTaiKhoan;
	private String matKhau;
	private String tenKhachHang;
	private String gioitinh;
	private String soDienThoai;
	private String eMail;
	private Date ngaySinh;
	private String diaChi;
	private String vaiTro;
	
	public AccountModel()
	{
		
	}
	public AccountModel(String tenTaiKhoan, String matKhau, String tenKhachHang, String gioitinh, String soDienThoai,
			String eMail, Date ngaySinh, String diaChi, String vaiTro) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.tenKhachHang = tenKhachHang;
		this.gioitinh = gioitinh;
		this.soDienThoai = soDienThoai;
		this.eMail = eMail;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.vaiTro = vaiTro;
	}
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}
	
	
	
	
}
