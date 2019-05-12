package wasdev.sample;

public class Checkpoint {
	 private String _id;
	 private String _rev;
	 private String testerID = null;
	 private String number = null;
	 private String ans1 = null;
	 private String ans2 = null;
	 private String ans3 = null;
	 private String ans4 = null;
	 private String score = null;
	 private String timestamp = null;
	 
	 
	 public Checkpoint() {
		 this.testerID="";
		 this.number = "";
		 this.ans1 = "";
		 this.ans2 = "";
		 this.ans3 = "";
		 this.ans4 = "";
		 this.score = "";
		 this.timestamp = "";
		 
	 }
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_rev() {
		return _rev;
	}
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	public String getTesterID() {
		return testerID;
	}
	public void setTesterID(String testerID) {
		this.testerID = testerID;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getAns3() {
		return ans3;
	}
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	public String getAns4() {
		return ans4;
	}
	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getTimestamp(){
		return timestamp;
	}
	public void setTimestamp(String timestamp){
		this.timestamp=timestamp;
	}
	
	 
	 
}
