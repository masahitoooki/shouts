package dto;

// �������ݓ��e��ێ�����N���X
public class ShoutDTO {
	private String userName;	// ���[�U��
	private String icon;		// ���[�U�A�C�R��
	private String date;		// �������ݓ���
	private String writing;		// �������ݓ��e

	public ShoutDTO() {

	}

	public ShoutDTO(String userName, String icon, String date, String writing) {
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
	}

	// �e�����o�ϐ��� getter ����� setter
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

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
