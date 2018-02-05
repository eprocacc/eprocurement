'use strict';

describe('Controller Tests', function() {

    describe('SupplierNominee Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockSupplierNominee, MockSupplier;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockSupplierNominee = jasmine.createSpy('MockSupplierNominee');
            MockSupplier = jasmine.createSpy('MockSupplier');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'SupplierNominee': MockSupplierNominee,
                'Supplier': MockSupplier
            };
            createController = function() {
                $injector.get('$controller')("SupplierNomineeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipstergatwayApp:supplierNomineeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
