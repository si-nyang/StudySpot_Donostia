function applyFilter() {
    var filter = document.getElementById('category');
    var category = filter.value;
    var req = new XMLHttpRequest();
    req.open('GET', '/StudySpot_Donostia/LocationList?ajax=true&category=' + category, true);
    req.onload = function() {
        if (req.status === 200) {
            var table = document.getElementById('locationTable');
            var header = table.rows[0].outerHTML; //row 0 also header
            table.innerHTML = header + req.responseText;
        }
    }
    req.send();
}