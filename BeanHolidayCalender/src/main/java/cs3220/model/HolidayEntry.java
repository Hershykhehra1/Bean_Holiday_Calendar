package cs3220.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class HolidayEntry {
	static int idSeed = 1;
	private int id;
    private String date;
    
    @NotBlank(message = "All Fields are Required")
    private String name;
	private String formattedDate;
	
	public HolidayEntry(String date, String name) {
		this.id = idSeed++;
		this.date = date;
		this.name = name;
	}
	public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int compareTo(HolidayEntry other) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date thisDate = sdf.parse(this.date);
            Date otherDate = sdf.parse(other.getDate());
            return thisDate.compareTo(otherDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
	
}