var adminAddMatch = new Vue({
    el: '#app',
    data: {
        clubs: [],
        createMatchDto: {
            homeClubId: null,
            visitorClubId: null
        },
        message: '',
    },

    computed: {

    },

    methods: {
        getClubs: function () {
            return Vue.http.get('/club')
                .then(function (response) {
                    this.clubs = response.body.content;
                }.bind(this));
        },

        submit: function () {
            return Vue.http.post('/gameweek/' + getQueryVariable('id') + '/match', this.createMatchDto)
                .then(function (response) {
                    this.message = 'Pomyślnie dodano mecz ' + this.getClubNameById(this.createMatchDto.homeClubId) + ' - ' + this.getClubNameById(this.createMatchDto.visitorClubId);
                    this.createMatchDto.homeClubId = null;
                    this.createMatchDto.visitorClubId = null;
                }.bind(this));
        },

        getClubNameById: function (id) {
            for (var i=0, iLen=this.clubs.length; i<iLen; i++) {
                console.log(this.clubs[i].id, id);
                if (this.clubs[i].id === id) return this.clubs[i].name;
            }
            return '';
        }
    },

    mounted: function () {
        this.getClubs();
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