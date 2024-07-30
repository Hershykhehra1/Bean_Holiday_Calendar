## Project Structure

The project is divided into several components:

**Model:**
- **HolidayEntry.java**: This class represents a holiday entry with an ID, date, and name. It includes validation for required fields and a method for comparing dates.

**Components:**
- **DataComponent.java**: This component manages a list of holiday entries. It includes methods for adding, retrieving, and checking the existence of entries.

**Controllers:**
- **IndexController.java**: This controller handles requests for viewing, adding, updating, and deleting holiday entries. It includes methods for formatting dates and validating input.

**Templates:**
- **AddHoliday.ftlh**: A template for adding a new holiday entry. It includes a form for inputting the holiday date and name, with validation and error messages.
- **UpdateHoliday.ftlh**: A template for updating an existing holiday entry. It pre-fills the form with the current date and name of the holiday.
- **index.ftlh**: A template for displaying all holiday entries in a table format. It includes links for updating or deleting each entry.

**How It Works:**
- **Adding a Holiday**: Users can navigate to the "Add Holiday" page, fill out the form, and submit it to add a new holiday to the calendar.
- **Editing a Holiday**: Each holiday in the calendar has an "Update" link. Clicking this link takes the user to a form where they can update the date and name of the holiday.
- **Deleting a Holiday**: Each holiday also has a "Delete" link. Clicking this link removes the holiday from the calendar.
- **Viewing Holidays**: The main holiday calendar page displays all holidays in a table format, with options to update or delete each entry.

**How to Run:**
- **Setup**: Ensure you have a Java Servlet container (e.g., Apache Tomcat) set up.
- **Deployment**: Download or import the project into your IDE and select "Run on Server."
- **Access**: Navigate to the application URL (e.g., `http://localhost:8080/HolidayCalendar`) to interact with the holiday calendar.
