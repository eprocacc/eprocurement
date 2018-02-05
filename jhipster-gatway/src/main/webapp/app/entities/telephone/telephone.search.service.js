(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('TelephoneSearch', TelephoneSearch);

    TelephoneSearch.$inject = ['$resource'];

    function TelephoneSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/telephones/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
