package dto;

// ���[�U����ێ�����N���X
public class UserDTO {
	private String loginId;		// ���O�C��ID
	private String password;	// �p�X���[�h
	private String userName;	// ���[�U��
	private String icon;		// ���[�U�A�C�R��
	private String profile;		// �v���t�B�[��

	public UserDTO(){

	}

	public UserDTO(String loginId, String password, String userName, String icon, String profile) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
	}

	// �e�����o�ϐ��� getter ����� setter
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
