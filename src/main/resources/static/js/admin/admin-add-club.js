var adminAddClub = new Vue({
    el: '#app',
    data: {
        clubDto: {
            name: '',
        },
        message: '',
    },

    computed: {

    },

    methods: {

        submit: function () {
            return Vue.http.post('/club', this.clubDto)
                .then(function (response) {
                    this.message = 'Pomyślnie dodano klub - ' + this.clubDto.name;
                    this.clubDto.name = null;
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