(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('DigitalCertificateController', DigitalCertificateController);

    DigitalCertificateController.$inject = ['DigitalCertificate', 'DigitalCertificateSearch'];

    function DigitalCertificateController(DigitalCertificate, DigitalCertificateSearch) {

        var vm = this;

        vm.digitalCertificates = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            DigitalCertificate.query(function(result) {
                vm.digitalCertificates = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            DigitalCertificateSearch.query({query: vm.searchQuery}, function(result) {
                vm.digitalCertificates = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
