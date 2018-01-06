var adminPlayerDetails = new Vue({
    el: '#app',
    data: {
        player: {
            name: '',
            surname: '',
            position: '',
            price: null,
            shirtNumber: null,
            points: 0,
            club: {
              name: '',
            },
        }
    },

    computed: {

    },

    methods: {
        getPlayer: function () {
            return Vue.http.get('/player/' + getQueryVariable('id'))
                .then(function (response) {
                    this.player = response.body;
                }.bind(this));
        },

        getPositionName: function (position) {
            if ('GOALKEEPER' === position) {
                return 'Bramkarz';
            }
            if ('DEFENDER' === position) {
                return 'Obrońca';
            }
            if ('MIDFIELDER' === position) {
                return 'Pomocnik';
            }
            if ('FORWARD' === position) {
                return 'Napastnik';
            }
        }
    },

    mounted: function () {
        this.getPlayer();
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