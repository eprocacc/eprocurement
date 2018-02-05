(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('DistrictController', DistrictController);

    DistrictController.$inject = ['District', 'DistrictSearch'];

    function DistrictController(District, DistrictSearch) {

        var vm = this;

        vm.districts = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            District.query(function(result) {
                vm.districts = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            DistrictSearch.query({query: vm.searchQuery}, function(result) {
                vm.districts = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
