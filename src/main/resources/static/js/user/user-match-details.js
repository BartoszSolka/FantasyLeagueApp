var userMatchDetails = new Vue({
    el: '#app',
    data: {
        match: {
            home: {
                name: '',
            },
            visitor: {
                name: '',
            },
            homeGoals: [],
            visitorGoals: [],
        },
    },

    computed: {

    },

    methods: {
        getMatch: function () {
            return Vue.http.get('/match/' + getQueryVariable('id'))
                .then(function (response) {
                    this.match = response.body;
                }.bind(this));
        },

        getGoalDescription: function (goals, n) {
            if (goals.length >= n) {
                var goal = goals[n - 1];
                return goal.minuteOfGame + '\'' + ' ' + this.getPlayerName(goal.scoredBy) + (goal.assistedBy ? (' (' + this.getPlayerName(goal.assistedBy) + ')') : '');
            } else {
                return '';
            }
        },

        getPlayerName: function (player) {
            if (player.name && player.surname) {
                return player.name.slice(0,1) + '. ' + player.surname;
            } else if (player.surname) {
                return player.surname;
            } else if (player.name) {
                return player.name;
            }
            return '';
        }
    },

    mounted: function () {
        this.getMatch();
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