<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <title>Update Holiday</title>
    <link rel="stylesheet" type="text/css" href="../css/Styles.css">
</head>
<body>
    <h2>Update Holiday</h2>
    <form action="/update/${entry.id}" method="post">
        <label for="day">Holiday Date:</label> 
        <select id="day" name="day">
            <#list 1..31 as dayNum>
                <option value="${dayNum?string('00')}" 
                        <#if dayNum?string('00') == day>${"selected"}</#if>>
                    ${dayNum?string('00')}
                </option>
            </#list>
        </select>

        <select id="month" name="month">
            <#list 1..12 as monthNum>
                <#assign monthName = ['', 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']>
                <option value="${monthNum?string('00')}" 
                        <#if monthNum?string('00') == month>${"selected"}</#if>>
                    ${monthName[monthNum]}
                </option>
            </#list>
        </select>

        <select id="year" name="year">
            <option value="2024">2024</option>
        </select>
        <br> 
        Holiday Name: <@spring.formInput "entry.name" /><br><@spring.showErrors "<br />" "color: red;" />
        <div class="error-message">${errorMessage!}</div>
        <button type="submit">Update Holiday</button>
    </form>
</body>
</html>
