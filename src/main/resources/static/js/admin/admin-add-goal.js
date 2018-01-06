var adminAddGameweek = new Vue({
    el: '#app',
    data: {
        createGameweekDto: {
            name: '',
        },
        message: '',
    },

    computed: {

    },

    methods: {

        submit: function () {
            return Vue.http.post('/gameweek', this.createGameweekDto)
                .then(function (response) {
                    this.message = 'Pomyślnie dodano kolejkę - ' + this.createGameweekDto.name;
                    this.createGameweekDto.name = null;
                }.bind(this));
        },
    },

    mounted: function () {

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