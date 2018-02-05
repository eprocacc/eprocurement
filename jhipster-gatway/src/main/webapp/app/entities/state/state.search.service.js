(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('StateSearch', StateSearch);

    StateSearch.$inject = ['$resource'];

    function StateSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/states/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
