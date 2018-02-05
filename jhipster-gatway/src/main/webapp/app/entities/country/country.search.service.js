(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('CountrySearch', CountrySearch);

    CountrySearch.$inject = ['$resource'];

    function CountrySearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/countries/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
