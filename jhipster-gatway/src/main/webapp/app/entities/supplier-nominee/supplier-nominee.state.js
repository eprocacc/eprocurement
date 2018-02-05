(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('supplier-nominee', {
            parent: 'entity',
            url: '/supplier-nominee',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.supplierNominee.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier-nominee/supplier-nominees.html',
                    controller: 'SupplierNomineeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supplierNominee');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('supplier-nominee-detail', {
            parent: 'supplier-nominee',
            url: '/supplier-nominee/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.supplierNominee.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier-nominee/supplier-nominee-detail.html',
                    controller: 'SupplierNomineeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supplierNominee');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'SupplierNominee', function($stateParams, SupplierNominee) {
                    return SupplierNominee.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'supplier-nominee',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('supplier-nominee-detail.edit', {
            parent: 'supplier-nominee-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-nominee/supplier-nominee-dialog.html',
                    controller: 'SupplierNomineeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SupplierNominee', function(SupplierNominee) {
                            return SupplierNominee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier-nominee.new', {
            parent: 'supplier-nominee',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-nominee/supplier-nominee-dialog.html',
                    controller: 'SupplierNomineeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                registered: null,
                                certChain: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('supplier-nominee', null, { reload: 'supplier-nominee' });
                }, function() {
                    $state.go('supplier-nominee');
                });
            }]
        })
        .state('supplier-nominee.edit', {
            parent: 'supplier-nominee',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-nominee/supplier-nominee-dialog.html',
                    controller: 'SupplierNomineeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SupplierNominee', function(SupplierNominee) {
                            return SupplierNominee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier-nominee', null, { reload: 'supplier-nominee' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier-nominee.delete', {
            parent: 'supplier-nominee',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-nominee/supplier-nominee-delete-dialog.html',
                    controller: 'SupplierNomineeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SupplierNominee', function(SupplierNominee) {
                            return SupplierNominee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier-nominee', null, { reload: 'supplier-nominee' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
