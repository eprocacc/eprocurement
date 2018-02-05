(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('supplier', {
            parent: 'entity',
            url: '/supplier?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.supplier.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier/suppliers.html',
                    controller: 'SupplierController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supplier');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('supplier-detail', {
            parent: 'supplier',
            url: '/supplier/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.supplier.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier/supplier-detail.html',
                    controller: 'SupplierDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supplier');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Supplier', function($stateParams, Supplier) {
                    return Supplier.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'supplier',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('supplier-detail.edit', {
            parent: 'supplier-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier/supplier-dialog.html',
                    controller: 'SupplierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Supplier', function(Supplier) {
                            return Supplier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier.new', {
            parent: 'supplier',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier/supplier-dialog.html',
                    controller: 'SupplierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                applicantType: null,
                                type: null,
                                name: null,
                                firstName: null,
                                middleName: null,
                                lastName: null,
                                socialStatus: null,
                                designation: null,
                                regnNumber: null,
                                pan: null,
                                panName: null,
                                grossTurnOver: null,
                                supplierStatus: null,
                                ssiYn: null,
                                ssiRegnNumber: null,
                                panStatus: null,
                                msmeYn: null,
                                msmeRegnNumber: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('supplier', null, { reload: 'supplier' });
                }, function() {
                    $state.go('supplier');
                });
            }]
        })
        .state('supplier.edit', {
            parent: 'supplier',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier/supplier-dialog.html',
                    controller: 'SupplierDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Supplier', function(Supplier) {
                            return Supplier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier', null, { reload: 'supplier' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier.delete', {
            parent: 'supplier',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier/supplier-delete-dialog.html',
                    controller: 'SupplierDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Supplier', function(Supplier) {
                            return Supplier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier', null, { reload: 'supplier' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
