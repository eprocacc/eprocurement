(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('AddressSearch', AddressSearch);

    AddressSearch.$inject = ['$resource'];

    function AddressSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/addresses/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
