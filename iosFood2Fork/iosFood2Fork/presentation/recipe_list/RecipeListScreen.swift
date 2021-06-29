//
//  RecipeListScreen.swift
//  iosFood2Fork
//
//  Created by Enes Tekin on 23.06.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    // dependencies
        private let networkModule: NetworkModule
        private let cacheModule: CacheModule
        private let searchRecipesModule: SearchRecipesModule
        private let foodCategories: [FoodCategory]
        
        @ObservedObject var viewModel: RecipeListViewModel
        
        init(
            networkModule: NetworkModule,
            cacheModule: CacheModule
        ) {
            self.networkModule = networkModule
            self.cacheModule = cacheModule
            self.searchRecipesModule = SearchRecipesModule(
                networkModule: self.networkModule,
                cacheModule: self.cacheModule
            )
            let foodCategoryUtil = FoodCategoryUtil()
            self.viewModel = RecipeListViewModel(
                searchRecipes: searchRecipesModule.searchRecipes,
                foodCategoryUtil: foodCategoryUtil
            )
            self.foodCategories = foodCategoryUtil.getAllFoodCategories()
        }
        
        var body: some View {
            NavigationView{
                ZStack{
                    VStack{
                        SearchAppBar(
                            query: viewModel.state.query,
                            selectedCategory: viewModel.state.selectedCategory,
                            foodCategories: foodCategories,
                            onTriggerEvent: { event in
                                viewModel.onTriggerEvent(stateEvent: event)
                            }
                        )
                        List {
                            ForEach(viewModel.state.recipes, id: \.self.id){ recipe in
                                ZStack{
                                    VStack{
                                        RecipeCard(recipe: recipe)
                                            .onAppear(perform: {
                                                if viewModel.shouldQueryNextPage(recipe: recipe){
                                                    viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                                                }
                                            })
                                    }
                                    NavigationLink(
                                        destination: RecipeDetailScreen(recipeId: Int(recipe.id), cacheModule: self.cacheModule)
                                    ){
                                        // workaround for hiding arrows
                                        EmptyView()
                                    }.hidden().frame(width: 0)
                                }
                                .listRowInsets(EdgeInsets())
                                .padding(.top, 10)
                            }
                        }
                        .listStyle(PlainListStyle())
                        .background(Color.gray)
                    }
                    if viewModel.state.isLoading {
                        ProgressView("Searching recipes...")
                    }
                    
                }
                
            
                .navigationBarHidden(true)
                .alert(isPresented: $viewModel.showDialog, content: {
                    let first = viewModel.state.queue.peek()!
                    return GenericMessageInfoAlert().build(
                        message: first,
                    onRemoveHeadMessage: {
                    viewModel.onTriggerEvent(
                        stateEvent: RecipeListEvents.OnRemoveHeadMessageFromQueue()
                    )
                                                           } )
                })
            }
        }
    }
