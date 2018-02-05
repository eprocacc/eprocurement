(function() {
    'use strict';
    angular
        .module('jhipstergatwayApp')
        .factory('DigitalCertificate', DigitalCertificate);

    DigitalCertificate.$inject = ['$resource', 'DateUtils'];

    function DigitalCertificate ($resource, DateUtils) {
        var resourceUrl =  'supplierregisteration/' + 'api/digital-certificates/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.termStartDate = DateUtils.convertDateTimeFromServer(data.termStartDate);
                        data.termEndDate = DateUtils.convertDateTimeFromServer(data.termEndDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
