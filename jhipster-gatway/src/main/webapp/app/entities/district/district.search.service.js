(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('DistrictSearch', DistrictSearch);

    DistrictSearch.$inject = ['$resource'];

    function DistrictSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/districts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
