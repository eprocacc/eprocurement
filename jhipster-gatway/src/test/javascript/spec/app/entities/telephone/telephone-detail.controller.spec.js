'use strict';

describe('Controller Tests', function() {

    describe('Telephone Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockTelephone, MockSupplierGeneralInfo;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockTelephone = jasmine.createSpy('MockTelephone');
            MockSupplierGeneralInfo = jasmine.createSpy('MockSupplierGeneralInfo');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Telephone': MockTelephone,
                'SupplierGeneralInfo': MockSupplierGeneralInfo
            };
            createController = function() {
                $injector.get('$controller')("TelephoneDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipstergatwayApp:telephoneUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
