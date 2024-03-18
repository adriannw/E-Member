function makeArray(n) {
	this.length = n;
	return this;
}

function daysOfMonth(year, month) {
	var days = new makeArray(12);

	days[1] = 31;  days[2] = 28;  days[3] = 31;
	days[4] = 30;  days[5] = 31;  days[6] = 30;
	days[7] = 31;  days[8] = 31;  days[9] = 30;
	days[10] = 31; days[11] = 30; days[12] = 31;

	if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
		days[2] = 29;

	return days[month];
}

function myDate(year, month, day) {
	this.year = year;
	this.month = month;
	this.day = day;
}

function prevMonth(date) {
	if(date.month == 1) {
		date.year--; date.month = 12;
	} else {
		date.month--;
	}
}

function nextMonth(date) {
	if(date.month == 12) {
		date.year++; date.month = 1;
	} else {
		date.month++;
	}
}

function dateSub(date1, date2) {
	var n = date1.day - date2.day;
	var date3;
	var sign = 1;

	if(date1.year < date2.year ||
		date1.year == date2.year && date1.month < date2.month ){
		sign = -1;
		date3 = date1;
		date1 = date2;
		date2 = date3;
	}
	while(!(date1.year == date2.year && date1.month == date2.month)) {
		n += daysOfMonth(date2.year, date2.month);
		nextMonth(date2);
	}
	return sign * n;
}

function dateAdd(date, n) {
	date.day += n;
	while(date.day <=0 || date.day > daysOfMonth(date.year, date.month)) {
		if(date.day <= 0){
			prevMonth(date);
			date.day += daysOfMonth(date.year, date.month);
		} else {
			date.day -= daysOfMonth(date.year, date.month);
			nextMonth(date);
		}
	}
}


function calculateAge( birthDay, birthMonth, birthYear )
{
    var todayDate = new Date();
	var year = todayDate.getYear() + 1900;
	var month = todayDate.getMonth() + 1;
	var day = todayDate.getDate();
    var thisDay = new myDate( year, month, day );

    var birthdayDate = new myDate( birthYear, birthMonth, birthDay );
    var age = floor( dateSub( todayDate, birthdayDate ) / 365 );
    alert( 'age = ' + age );
    return age;
}