var userClubs = new Vue({
    el: '#app',
    data: {
        clubs: []
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

        clubIdLink: function (club) {
            return "/club/" + club.id;
        },
    },

    mounted: function () {
        this.getClubs();
    },

})