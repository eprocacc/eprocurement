(function() {
    'use strict';
    angular
        .module('jhipstergatwayApp')
        .factory('SupplierGeneralInfo', SupplierGeneralInfo);

    SupplierGeneralInfo.$inject = ['$resource', 'DateUtils'];

    function SupplierGeneralInfo ($resource, DateUtils) {
        var resourceUrl =  'supplierregisteration/' + 'api/supplier-general-infos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.expiryDate = DateUtils.convertDateTimeFromServer(data.expiryDate);
                        data.renewedDate = DateUtils.convertDateTimeFromServer(data.renewedDate);
                        data.submissionDate = DateUtils.convertDateTimeFromServer(data.submissionDate);
                        data.approvaldate = DateUtils.convertDateTimeFromServer(data.approvaldate);
                        data.emailOTPDate = DateUtils.convertDateTimeFromServer(data.emailOTPDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
