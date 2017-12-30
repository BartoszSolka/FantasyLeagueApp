var adminUsers = new Vue({
    el: '#app',
    data: {
        users: []
    },

    computed: {

    },

    methods: {
        getUsers: function () {
            return Vue.http.get('/user')
                .then(function (response) {
                    this.users = response.body.content;
                }.bind(this));
        },
    },

    mounted: function () {
        this.getUsers();
    },

})