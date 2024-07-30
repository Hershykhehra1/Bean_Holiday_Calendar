package cs3220;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cs3220.model.HolidayEntry;

@Component
public class DataComponent {
    private List<HolidayEntry> entries;

    public DataComponent() {
        entries = new ArrayList<>();
        entries.add(new HolidayEntry("2024-01-01", "New Year's Day"));
        entries.add(new HolidayEntry("2024-01-15", "Martin Luther King Jr. Day"));
        entries.add(new HolidayEntry("2024-02-12", "Lincoln's Birthday"));
        entries.add(new HolidayEntry("2024-02-19", "President's Day"));
        entries.add(new HolidayEntry("2024-07-04", "Independence Day"));
        entries.add(new HolidayEntry("2024-09-02", "Labor Day"));
        entries.add(new HolidayEntry("2024-11-28", "Thanksgiving Day"));
        entries.add(new HolidayEntry("2024-12-25", "Christmas Day"));
    }

    public HolidayEntry getEntryById(int id) {
        for (HolidayEntry entry : entries) {
            if (entry.getId() == id) {
                return entry;
            } else {
                continue;
            }
        }
        return null;
    }

    public boolean isHolidayExists(String date, String name) {
        for (HolidayEntry entry : entries) {
            if (entry.getDate().equals(date) || entry.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addEntry(HolidayEntry entry) {
        entries.add(entry);
    }

    public List<HolidayEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<HolidayEntry> entries) {
        this.entries = entries;
    }
}
