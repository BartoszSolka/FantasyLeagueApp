var userGameweekDetails = new Vue({
    el: '#app',
    data: {
        gameweek: {
            matches: [],
        },
    },

    computed: {

    },

    methods: {
        getMatches: function () {
            console.log(getQueryVariable('id'));
            return Vue.http.get('/gameweek/' + getQueryVariable('id'))
                .then(function (response) {
                    this.gameweek = response.body;
                }.bind(this));
        },

        matchIdLink: function (match) {
            return "/user-match-details?id=" + match.id;
        },
    },

    mounted: function () {
        this.getMatches();
    },

})

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}