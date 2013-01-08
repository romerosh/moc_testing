package data.orm;

public class Mark extends ORMObject {
	private int ID;
	private int Mark;
	private int student_id;
	private int subject_id;
	
	public Mark ()
	{
		
	}
	public Mark(Subject s,Student st, int mark) {
		this.subject_id = s.getID();
		this.student_id = st.getID();
		this.Mark = mark;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getMark() {
		return Mark;
	}

	public void setMark(int mark) {
		Mark = mark;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

}
