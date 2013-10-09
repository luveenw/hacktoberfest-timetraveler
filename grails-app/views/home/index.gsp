<!DOCTYPE html>
<html>
<head>
    <title><g:message code="page.title.settings.home"/></title>
    <meta name="layout" content="main"/>
    <r:require module="application"/>
</head>
<body>
    <h1>Time Traveler</h1>

    <div id="chart_div"></div>

    <r:script>
        google.load('visualization', '1', {'packages':['motionchart']});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Fruit');
            data.addColumn('date', 'Date');
            data.addColumn('number', 'Sales');
            data.addColumn('number', 'Expenses');
            data.addColumn('string', 'Location');
            data.addRows([
                ['Apples',  new Date (1988,0,1), 1000, 300, 'East'],
                ['Oranges', new Date (1988,0,1), 1150, 200, 'West'],
                ['Bananas', new Date (1988,0,1), 300,  250, 'West'],
                ['Apples',  new Date (1989,6,1), 1200, 400, 'East'],
                ['Oranges', new Date (1989,6,1), 750,  150, 'West'],
                ['Bananas', new Date (1989,6,1), 788,  617, 'West']
            ]);
            var chart = new google.visualization.MotionChart(document.getElementById('chart_div'));
            chart.draw(data, {width: 600, height:300});
        }
    </r:script>

</body>
</html>