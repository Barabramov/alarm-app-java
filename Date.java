
public class Date {
private String day;
private String month;
private String year;

public Date(String day, String month ,String year ) {
	this.day=day;
	this.month=month;
	this.year=year;	
}

public boolean equals(Date otherDate) {
	return (this.day.equals(otherDate.day)&& this.month.equals(otherDate.month)&& this.year.equals(otherDate.year));
}

@Override
public String toString() {
	return this.day+"."+this.month+"."+this.year;
}
}
