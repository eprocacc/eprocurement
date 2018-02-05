'use strict';

describe('Controller Tests', function() {

    describe('Supplier Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockSupplier, MockAddress, MockSupplierNominee, MockSupplierGeneralInfo;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockSupplier = jasmine.createSpy('MockSupplier');
            MockAddress = jasmine.createSpy('MockAddress');
            MockSupplierNominee = jasmine.createSpy('MockSupplierNominee');
            MockSupplierGeneralInfo = jasmine.createSpy('MockSupplierGeneralInfo');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Supplier': MockSupplier,
                'Address': MockAddress,
                'SupplierNominee': MockSupplierNominee,
                'SupplierGeneralInfo': MockSupplierGeneralInfo
            };
            createController = function() {
                $injector.get('$controller')("SupplierDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipstergatwayApp:supplierUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
