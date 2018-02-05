'use strict';

describe('Controller Tests', function() {

    describe('SupplierGeneralInfo Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockSupplierGeneralInfo, MockSupplier, MockAddress, MockTelephone;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockSupplierGeneralInfo = jasmine.createSpy('MockSupplierGeneralInfo');
            MockSupplier = jasmine.createSpy('MockSupplier');
            MockAddress = jasmine.createSpy('MockAddress');
            MockTelephone = jasmine.createSpy('MockTelephone');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'SupplierGeneralInfo': MockSupplierGeneralInfo,
                'Supplier': MockSupplier,
                'Address': MockAddress,
                'Telephone': MockTelephone
            };
            createController = function() {
                $injector.get('$controller')("SupplierGeneralInfoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipstergatwayApp:supplierGeneralInfoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
