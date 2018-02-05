(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('DigitalCertificateSearch', DigitalCertificateSearch);

    DigitalCertificateSearch.$inject = ['$resource'];

    function DigitalCertificateSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/digital-certificates/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
