var userGameweeks = new Vue({
    el: '#app',
    data: {
        gameweeks: []
    },

    computed: {

    },

    methods: {
        getGameweeks: function () {
            return Vue.http.get('/gameweek')
                .then(function (response) {
                    this.gameweeks = response.body.content;
                }.bind(this));
        },

        gameweekIdLink: function (gameweek) {
            return "/user-gameweek-details?id=" + gameweek.id;
        },
    },

    mounted: function () {
        this.getGameweeks();
    },

})