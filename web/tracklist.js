$(document).ready(function () {

    $('#retrieve-resources').click(function () {
        var displayResources = $('#display-resources');

        displayResources.text('Loading data from JSON source...');

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/track/allTracks",
            success: function(result)
            {
                console.log(result);
                var output="<table><thead><tr><th>Name</th><th>Album</th><th>Artist</th></thead><tbody>";
                for (var i in result)
                {
                    output+="<tr><td>" + result[i].name + "</td><td>" + result[i].album + "</td><td>" + result[i].artist + "</td></tr>";
                }
                output+="</tbody></table>";

                displayResources.html(output);
                $("table").addClass("table");
            }
        });

    });
});