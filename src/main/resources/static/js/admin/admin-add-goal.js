var adminAddGoal = new Vue({
    el: '#app',
    data: {
        goalDto: {
            matchId: getQueryVariable('id'),
        },
        message: '',
        match: {
            home: {},
            visitor: {},
        },
        players: [],
    },

    methods: {

        submit: function () {
            return Vue.http.post('/goal', this.goalDto)
                .then(function (response) {
                    this.message = 'Pomyślnie dodano bramkę';
                }.bind(this));
        },

        getMatch: function () {
            return Vue.http.get('/match/' + getQueryVariable('id'))
                .then(function (response) {
                    this.match = response.body;
                }.bind(this));
        },

        getPlayers: function () {
            var clubId;
            if (!this.goalDto.matchSide) return [];
            if (this.goalDto.matchSide === 'HOME') {
                clubId = this.match.home.id;
            }
            if (this.goalDto.matchSide === 'VISITOR') {
                clubId = this.match.visitor.id;
            }
            return Vue.http.get('/club/' + clubId + '/players')
                .then(function (response) {
                    console.log(response.body);
                    this.players = response.body;
                }.bind(this));
        },
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