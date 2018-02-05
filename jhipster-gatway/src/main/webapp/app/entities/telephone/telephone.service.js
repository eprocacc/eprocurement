(function() {
    'use strict';
    angular
        .module('jhipstergatwayApp')
        .factory('Telephone', Telephone);

    Telephone.$inject = ['$resource', 'DateUtils'];

    function Telephone ($resource, DateUtils) {
        var resourceUrl =  'supplierregisteration/' + 'api/telephones/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.mobileOTPDate = DateUtils.convertDateTimeFromServer(data.mobileOTPDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
